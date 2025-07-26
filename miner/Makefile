all: miner

miner: main.c siggen.c siggen.h mempool.c nonce.c reader.c event_q.c
	gcc -Wall -g -o miner main.c siggen.c transactions.c mempool.c nonce.c reader.c event_q.c -lpthread

clean:
	rm -f miner
