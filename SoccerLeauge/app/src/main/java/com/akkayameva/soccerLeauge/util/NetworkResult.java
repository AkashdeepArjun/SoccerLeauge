package com.akkayameva.soccerLeauge.util;

import androidx.annotation.NonNull;

public class NetworkResult<T> {

    @NonNull
    T data;
    String message;

    private NetworkResult() {
    }


    public class Loading extends NetworkResult<T> {

    }
    public final class Success extends NetworkResult<T> {


        public Success(T data) {
            this.data = data;
        }
    }

    public class Failure extends NetworkResult<T> {


        public Failure(String message, T data) {
            this.message = message;
            this.data = data;
        }
    }
}