//Solutions from A3
//https://git.cs.dal.ca/courses/2022-summer/csci-3120/assignment-3/solution/-/blob/main/miner/mempool.h
//
// Created by Alex Brodsky on 2021-05-19.
//

#ifndef MINER_MEMPOOL_H
#define MINER_MEMPOOL_H
#include "transactions.h"

extern void mempool_init();
extern void mempool_add(trx_t *trx);
extern trx_t * mempool_remove(unsigned int id);
extern trx_t * mempool_select(int max_size);
extern void mempool_age(void (*callback)(trx_t *, int));

#endif //MINER_MEMPOOL_H
