/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asseco.websocket;

import com.asseco.dao.UserDAO;
import com.asseco.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author atanasij.josifoski
 */
@ServerEndpoint("/notify")
public class PushNotify {

//    private static final List<Session> SESSIONS = new ArrayList<>();
    private static final Map<User, Set<Session>> sessions = new ConcurrentHashMap<>();
    
    

    @EJB
    UserDAO userDao;

    @OnOpen
    public void onOpen(Session session) {
//        SESSIONS.add(session);
//        sendAll("new member joined");
    }

    @OnClose
    public void onClose(Session session) {
        sessions.values().forEach(v -> v.removeIf(e -> e.equals(session)));
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
//        System.out.println(message);
        User user = userDao.find(message);
//        sessions.put(user, session);
        sessions.computeIfAbsent(user, v -> ConcurrentHashMap.newKeySet()).add(session);
    }

    @OnError
    public void onError(Throwable error) {
        //TODO handle error
    }

    public static void send(Set<User> users, PushMessage message) {
        Set<Session> userSessions;

        synchronized (sessions) {
            userSessions = sessions.entrySet().stream()
                    .filter(e -> users.contains(e.getKey()))
                    .flatMap(e -> e.getValue().stream())
                    .collect(Collectors.toSet());
        }
        
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final String json;
        json = gson.toJson(message);
        
        for (Session userSession : userSessions) {
            if (userSession.isOpen()) {
                userSession.getAsyncRemote().sendText(json);
            }
        }
    }

}
