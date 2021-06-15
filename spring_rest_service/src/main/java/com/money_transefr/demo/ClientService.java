package com.money_transefr.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientService{

    // Хранилище клиентов
    private static final Map<Integer, Account> ACCOUNT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger ACCOUNT_ID_HOLDER = new AtomicInteger();

        public void create(Account account) {
        final int clientId = ACCOUNT_ID_HOLDER.incrementAndGet();
        account.setId(clientId);
        ACCOUNT_REPOSITORY_MAP.put(clientId, account);
    }

        public List<Account> readAll() {
        return new ArrayList<>(ACCOUNT_REPOSITORY_MAP.values());
    }

    public Account read(int id) {
        return ACCOUNT_REPOSITORY_MAP.get(id);
    }

    public boolean update(Account account, int id) {
        if (ACCOUNT_REPOSITORY_MAP.containsKey(id)) {
            account.setId(id);
            ACCOUNT_REPOSITORY_MAP.put(id, account);
            return true;
        }

        return false;
    }

    public boolean delete(int id) {
        return ACCOUNT_REPOSITORY_MAP.remove(id) != null;
    }
}

