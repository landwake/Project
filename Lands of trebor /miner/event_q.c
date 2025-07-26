
//start reader thread - (reads from stdin and puts in queue)                                                              
//start main thread - (pulls from queue)                                                                                  


#define END_EVENT "END"
#define MINE_EVENT "MINE"
#define TRX_EVENT "TRX"
#define BLK_EVENT "BLK"
#define EPOCH_EVENT "EPOCH"


#include "event_q.h"



extern void e_enqueue(char* thing, event_list *list){
  pthread_mutex_lock(&mutex);
  event* newEvent = malloc(sizeof(event));
  newEvent->e = thing;
  assert(list);
  assert(newEvent);

  if (list->head) {
    list->tail->next = newEvent;
  } else {
    list->head = newEvent;
  }
  list->tail = newEvent;
  newEvent->next = NULL;



  //pthread_cond_signal(&cond);
  pthread_mutex_unlock(&mutex);
  //
}


extern event* e_dequeue(event_list *list){
    while (!list->head){
    //pthread_cond_wait(&cond, &mutex);
  }

  pthread_mutex_lock(&mutex);
  
  assert(list);

  event *e = list->head;
  if (e) {
    list->head = e->next;
  }



  pthread_mutex_unlock(&mutex);
  return e;  
  


}
