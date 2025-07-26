#ifndef EVENT_Q_H
#define EVENT_Q_H


#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <pthread.h>

typedef struct event{
  struct event *next;
  char *e;
}event;

typedef struct event_list{
  event *head;
  event *tail;
}event_list;


pthread_mutex_t mutex;
pthread_cond_t cond;


extern void e_enqueue(char*, event_list*);
extern event* e_dequeue(event_list*);

#endif
