package com.example.retrofitpost;


import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    // This variable will be used to store a reference to a Retrofit object
    // that is used to make API requests.
    public static ApiInterface getClient() {
        //static factory method(design pattern)
        //A factory method is a method that returns an object of a class.

        //Defines a public static method called getClient() that returns an ApiInterface object.

        //The purpose of this factory method is to encapsulate the creation of the ApiInterface object,
        // and to provide a convenient way to create instances of this interface without having
        // to know how it is implemented.

        //change our base url
        if (retrofit==null) {
            retrofit = new Retrofit.Builder() //create Retrofit.Builder object
                    .baseUrl(HttpUrl.parse("http://mobileappdatabase.in/"))
                    //set base url
                    .addConverterFactory(GsonConverterFactory.create())
                    //create Converter.Factory that converts Json data to Java objects
                    .build();
        }
        //creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api;
        //Finally, the code returns the ApiInterface object so that it can be used to make API requests.

        // This ApiInterface object will have methods that correspond to the API endpoints
        // defined in the interface, and when these methods are called,
        // Retrofit will handle the network request and return the response as a Java object.
    }
}
