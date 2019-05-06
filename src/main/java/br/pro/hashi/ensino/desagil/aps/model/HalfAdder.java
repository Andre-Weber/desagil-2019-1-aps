package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;
    private final NandGate nandLeft;
    private final NandGate nandCarry;


    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandTop = new NandGate();
        nandBottom = new NandGate();
        nandLeft = new NandGate();
        nandRight = new NandGate();


        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nandTop.connect(1, nandLeft);
        nandBottom.connect(0, nandLeft);

        nandCarry = new NandGate();

        nandCarry.connect(1, nandLeft);
        nandCarry.connect(0, nandLeft);


    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0 && outputPin != 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }

        if (outputPin == 1) {
            return nandCarry.read();
        }

        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandBottom.connect(1, emitter);
                nandLeft.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
