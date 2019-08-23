package com.example.apptec.Api;

import com.example.apptec.Modelos.ModeloHoy;
import com.example.apptec.Modelos.ModeloJueves;
import com.example.apptec.Modelos.ModeloLogin;
import com.example.apptec.Modelos.ModeloLu;
import com.example.apptec.Modelos.ModeloMartes;
import com.example.apptec.Modelos.ModeloMiercoles;
import com.example.apptec.Modelos.ModeloPerfil;
import com.example.apptec.Modelos.ModeloSabado;
import com.example.apptec.Modelos.ModeloToken;
import com.example.apptec.Modelos.ModeloViernes;
import com.example.apptec.Modelos.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebServiceApi {


    @FormUrlEncoded
    @POST("/oauth/token")
    Call<Token> obtenerToken(
        @Field("username") int username,
        @Field("password") int password,
        @Field("grant_type") String grantType
        );


    @POST("Api/Perfil/Show")
    Call<List<ModeloPerfil>> PerfilAlumno(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonDay")
    Call<List<ModeloHoy>>obtenerDay(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonMonday")
    Call<List<ModeloLu>> obtenerMonday(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonTuesday")
    Call<List<ModeloMartes>> obtenerTuesday(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonWednesday")
    Call<List<ModeloMiercoles>> obtenerWednesday(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonThursday")
    Call<List<ModeloJueves>> obtenerThusday(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonFriday")
    Call<List<ModeloViernes>> obtenerFriday(@Body ModeloToken modeloToken);

    @POST("Api/Class/LessonSaturday")
    Call<List<ModeloSabado>> obtenerSaturday(@Body ModeloToken modeloToken);






    @POST("/api/login/authenticate")
    Call<List<String>> obtenerToken(@Body ModeloLogin modeloLogin);

    @POST("/api/horario/dia")
    Call<List<ModeloHoy>>obtenerDia(@Body ModeloToken modeloToken);



    @POST("/api/horario/lunes")
    Call<List<ModeloLu>> obtenerLunes(@Body ModeloToken modeloToken);

    @POST("/api/horario/martes")
    Call<List<ModeloMartes>> obtenerMartes(@Body ModeloToken modeloToken);

    @POST("/api/horario/miercoles")
    Call<List<ModeloMiercoles>> obtenerMiercoles(@Body ModeloToken modeloToken);

    @POST("/api/horario/jueves")
    Call<List<ModeloJueves>> obtenerJueves(@Body ModeloToken modeloToken);

    @POST("/api/horario/viernes")
    Call<List<ModeloViernes>> obtenerViernes(@Body ModeloToken modeloToken);

    @POST("/api/horario/sabado")
    Call<List<ModeloSabado>> obtenerSabado(@Body ModeloToken modeloToken);



    @POST("/api/perfil/alumno")
    Call<List<ModeloPerfil>> obtenerPerfil(@Body ModeloToken modeloToken);


}
