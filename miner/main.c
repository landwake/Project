//https://git.cs.dal.ca/courses/2022-summer/csci-3120/assignment-3/solution/-/blob/main/miner/main.c
// solutions from A3

#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <string.h>

#include "transactions.h"
#include "mempool.h"
#include "siggen.h"
#include "nonce.h"
#include "reader.h"
#include "event_q.h"


#define END_EVENT "END"
#define MINE_EVENT "MINE"
#define TRX_EVENT "TRX"
#define BLK_EVENT "BLK"
#define EPOCH_EVENT "EPOCH"

//Had to change the scanf to sscanf to make it easier with the buffer 

static void trx_read(char* string, trx_t *trx) {
  int n = sscanf(string, "TRX %u %s %s %u %u", &trx->id, trx->payer,
		trx->payee, &trx->amount, &trx->fee);
  assert(n == 5);
}

static void trx_print(char *prefix, trx_t *trx) {
  printf("%s%d %s %s %d %d\n", prefix, trx->id, trx->payer,
	 trx->payee, trx->amount, trx->fee);
}

static void trx_age(trx_t *trx, int prio) {
  printf("Aging transaction (%d):", prio);
  trx_print("", trx);
}

int main() {
  
  trx_list_t *selected = transaction_list();
  unsigned int prev_id = 0;
  unsigned int prev_sig = 0;
  event_list e_list;
  //had to add this so it doens't do garbage
  e_list.head = 0;
  e_list.tail = 0;
  mempool_init();

  //start reader thread - (reads from stdin and puts in queue)
  //start main thread - (pulls from queue)
  
  start_reader(&e_list);

  for (;;) {

    //replace scanf with event e = event_dequeue();
    //put events into buffer
    
    event *events = e_dequeue(&e_list);
    char *buffer = events->e;
    

    if (strstr(buffer, END_EVENT)) {
    
      break;
    } else if (strstr(buffer, EPOCH_EVENT)) {

      mempool_age(trx_age);
    } else if (strstr(buffer, TRX_EVENT)) {
      trx_t * trx = transaction_new();
      trx_read(buffer, trx);
    
      trx_print("Adding transaction: ", trx);
      mempool_add(trx);
    } else if (strstr(buffer, BLK_EVENT)) {
      unsigned int block_id;
      unsigned int num_trx;
      trx_t trx;
      
      char* line = strtok(buffer, "\n");
      
      int n = sscanf(line, "BLK %u %u %i %u", &block_id, &prev_id,
		    &prev_sig, &num_trx);
      
      assert(n == 4);

      for (int i = 0; i < num_trx; i++) {
	line = strtok(NULL, "\n");
	int n = sscanf(line, "%u %s %s %u %u", &trx.id, trx.payer,
		trx.payee, &trx.amount, &trx.fee);
	assert(n == 5);

	trx_t *t = mempool_remove(trx.id);
	if (t != NULL) {
	  trx_print("Removing transaction: ", t);
	  transaction_delete(t);
	}
      }

      unsigned int nonce;
      prev_id = block_id;
      line = strtok(NULL, "\n");
      n = sscanf(line, "%i %i", &nonce, &prev_sig);
      assert(n == 2);
    }
    else if (strstr(buffer, MINE_EVENT)) {
      unsigned int id = prev_id + 1;
      unsigned int num_trx = 0;
      unsigned int available = 256 - 24; // 24 bytes in block without transactions
      int num_threads;
      int n = sscanf(buffer, "MINE %d", &num_threads);
      assert(n == 1);


      for (trx_t *t = mempool_select(available); t != NULL; t = mempool_select(available)) {
	available -= transaction_size(t);
	num_trx++;
	transaction_append(selected, t);
      }

      unsigned int sig = siggen_new();
      sig = siggen_int(sig, id);
      sig = siggen_int(sig, prev_id);
      sig = siggen_int(sig, prev_sig);
      sig = siggen_int(sig, num_trx);
      printf("Block mined: %d %d 0x%8.8x %d\n", id, prev_id, prev_sig, num_trx);

      for (int i = 0; i < num_trx; i++) {
	trx_t *t = transaction_pop(selected);
	sig = siggen_int(sig, t->id);
	sig = siggen_string(sig, t->payer);
	sig = siggen_string(sig, t->payee);
	sig = siggen_int(sig, t->amount);
	sig = siggen_int(sig, t->fee);
	trx_print("", t);
	transaction_delete(t);
      }

      unsigned int nonce = nonce_find(num_threads, sig);
      sig = siggen_int(sig, nonce);
      printf("0x%8.8x 0x%8.8x\n", nonce, sig);
      prev_id = id;
      prev_sig = sig;
    }
  }
  
  stop_reader(); 
  return 0;
}

