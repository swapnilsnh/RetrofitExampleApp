Steps

1) Add Dependencies to Build.gradle(app)

Retrofit dependencies:
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:convertor-gson:2.9.0'
implementation 'com.google.code.gson:gson:2.10.1'   (Used to convert Java object to JSON and vice versa)
implementation 'com.squareup.retrofit2:okHttp3:logging-interceptor:5.0.0'

2)Create a Retrofit Builder class

>Create a class ApiClient.java
 This will be used to retrieve Retrofit Object
>Create a private static variable retrofit
>Create a static method to retrieve retrofit object
 public static Retrofit getClient(){
	HttpLoggingInterceptor interceptor =  new HttpLoggingInterceptor();
	interceptor.setLevel(HttpLoggingInterceptor.Level.Body);
	OkHttpClient okHttpClient =  new OkHttpClient.Buider().addInterceptor(interceptor).build();

	retrofit =  new Retrofit.Builder()
			.baseUrl("https://reqres.in")
			.addConvertorFactory(GsonConvertorFactory.create())
			.client(okHttpClient)
			.build();
 }

3)Create ApiInteface.java interface
	Used for defining possible HTTP operations

	@GET("/api/unknown")
	Call<MultipleResource> doGetListResources();

	@POST("/api/users")
	Call<User> createUser(@Body User user);

	@GET("/api/users?")
	Call<UserList> doGetUserList("page") String page)  //https://reqres.in/api/users?&page=passedValue

	@FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);




-----------------------------------------------------------------------------------------------------------

Queries
* What is the use of Convertors?
* What is the use of HttpLoggingInterceptors?
*What is OkHttpClient?

