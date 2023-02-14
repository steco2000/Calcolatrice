package control;

public class CalculatorController {

    //il controller esporta tutti le operazioni per eseguire i vari conti

    public float sum(float op1, float op2){ return op1+op2; }

    public float sub(float op1, float op2){ return op1-op2; }

    public float multiply(float op1, float op2){ return op1*op2; }

    public float divide(float op1, float op2){ return op1/op2; }

    public float naturalLog(float op){ return (float) Math.log(op); }
}
