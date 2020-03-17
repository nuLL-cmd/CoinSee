package com.automatodev.coinSee.controller.callback;

import com.automatodev.coinSee.controller.entity.UserEntity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public interface FirestoreCallback {

    void onEventListener(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e);

    void onEventSucccess(UserEntity userEntity);
}
