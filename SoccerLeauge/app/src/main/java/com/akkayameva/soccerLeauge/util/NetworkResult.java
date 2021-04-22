package com.akkayameva.soccerLeauge.util;

import androidx.annotation.NonNull;

public abstract class NetworkResult<T> {



    private NetworkResult() {
    }


    public class Loading extends NetworkResult<T> {

    }
    public final class Success extends NetworkResult<T> {
        @NonNull
        private T data;
        public Success(T data) {
            this.data = data;
        }
    }

    public class Failure extends NetworkResult<T> {

        @NonNull
        T data;
        String message;

        public Failure(String message, T data) {
            this.message = message;
            this.data = data;
        }
    }
}