package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import service.ICalculadora;

public class Servidor extends UnicastRemoteObject implements ICalculadora {
    
    public Servidor() throws RemoteException{
        super();
    }

    @Override
    public double getSoma(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public double getSubtracao(double a, double b) throws RemoteException {
        return a-b;
    }

    @Override
    public double getMultiplicacao(double a, double b) throws RemoteException {
        return a*b;
    }

    @Override
    public double getDivisao(double a, double b) throws RemoteException {
        return a/b;
    }

    @Override
    public double getRaizQuadrada(double valor) throws RemoteException {
        return Math.sqrt(valor);
    }
    
    @Override
    public double getQuadrado(double valor) throws RemoteException {
        return Math.pow(valor,2);
    }
    
    //
    // EXECUTAR O OBJETO REMOTO
    //
    public static void main(String[] args) {
        try {
            ICalculadora obj = new Servidor();
            
            //Registrar a porta de comunicação
            //import java.rmi.registry.Registry;
            //import java.rmi.registry.LocateRegistry;
            Registry rg = LocateRegistry.createRegistry(ICalculadora.PORTA);
            
            rg.bind(ICalculadora.NOME, obj);
            
            System.out.println("Serviço em execução.");
        
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    
    
}














