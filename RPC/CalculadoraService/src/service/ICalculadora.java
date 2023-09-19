package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote {

    //
    // PARÂMETROS DA ARQUITETURA DISTRIBUÍDA
    //
    public static final int PORTA = 1099;
    public static final String NOME = "Serviço Calculadora";
    //URI = Uniform Resource Identifier
    public static final String URI = "127.0.0.1";
    
    //
    // ASSINATURA DOS MÉTODOS
    //
    public abstract double getSoma(double a, double b) throws RemoteException;
    public abstract double getSubtracao(double a, double b) throws RemoteException;
    public abstract double getMultiplicacao(double a, double b) throws RemoteException;
    public abstract double getDivisao(double a, double b) throws RemoteException;
    public abstract double getRaizQuadrada(double valor)  throws RemoteException;
    public abstract double getQuadrado(double valor)  throws RemoteException;
    
}
