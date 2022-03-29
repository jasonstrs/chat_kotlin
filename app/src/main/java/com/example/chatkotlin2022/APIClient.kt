package com.example.chatkotlin2022

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = Builder().addInterceptor(interceptor).build()
        val gson: Gson = GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create()
        retrofit = Builder()
                .baseUrl("http://tomnab.fr/chat-api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        return retrofit
    }
}