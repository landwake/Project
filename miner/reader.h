#ifndef MINER_READER_H
#define MINER_READER_H

#include "stdio.h"
#include "assert.h"
#include "event_q.h"

#include <pthread.h>

void *runner(void*);
void start_reader(event_list*);
void stop_reader();

#endif
