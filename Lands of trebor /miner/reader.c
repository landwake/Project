
#include <stdio.h>
#include "assert.h"
#include <pthread.h>
#include <string.h>

#include "event_q.h"
#include "reader.h"
#include "transactions.h"

#define END_EVENT "END"
#define MINE_EVENT "MINE"
#define TRX_EVENT "TRX"
#define BLK_EVENT "BLK"
#define EPOCH_EVENT "EPOCH"


pthread_t threads;


//PRODUCER
void *runner(void *arg){
  fflush(stdout);
  event_list *e = (event_list*) arg;
  for(;;){

    // don't wanna write to the same buffer every time (haha oops)
    char* buffer = malloc(500);

    fgets(buffer, 500, stdin);

    
    if (strstr(buffer, END_EVENT)) {
      printf("Received event END\n");
      
      e_enqueue(buffer, e);
      break;
    }
    else if (strstr(buffer, EPOCH_EVENT)) {
      printf("Received event EPOCH\n");
      e_enqueue(buffer, e);      
    }
    else if (strstr(buffer, TRX_EVENT)) {
      trx_t trx;
      int n = sscanf(buffer, "TRX %u %s %s %u %u", &trx.id, trx.payer,
		     trx.payee, &trx.amount, &trx.fee);
      printf("Received event TRX with ID %u\n", trx.id);
      e_enqueue(buffer, e);
    }
    else if (strstr(buffer, BLK_EVENT)) {
      
      unsigned int block_id;
      unsigned int num_trx;
      unsigned int prev_sig;
      unsigned int prev_id;
      
      int n = sscanf(buffer, "BLK %u %u %i %u", &block_id, &prev_id,
		     &prev_sig, &num_trx);
      assert(n == 4);
     
      sprintf(buffer, "BLK %u %u %i %u\n", block_id, prev_id,
	      prev_sig, num_trx);
      
      for(int i = 0;i<num_trx; i++){
	fgets(buffer + strlen(buffer),500, stdin);
      }
      
      fgets(buffer+ strlen(buffer), 500, stdin);
      
      printf("Received event BLK with ID %u\n", block_id);
      e_enqueue(buffer, e);
    }
    else if (strstr(buffer, MINE_EVENT)) {
      printf("Received event MINE\n");
      e_enqueue(buffer, e);

    }
    
    
  }
}

void start_reader(event_list *list){
  //create thread
  pthread_mutex_init(&mutex, NULL);
  pthread_create(&threads, NULL, runner, list);
  
  
  //


  
}

void stop_reader(){
  //joining threads
  pthread_join(threads, NULL);
  
}
