//package inputs;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.lang.reflect.Type;
//import java.util.Scanner;
//
//public class TypeInputs implements KeyListener {
//
//    private StringBuilder typedText;
//    private boolean inputted;
//
//
//    public void reloadTypeInputs() {
//        typedText = new StringBuilder();
//        inputted = false;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        Character inputChar = e.getKeyChar();
//        int typedTextLength = typedText.length();
//        if(inputChar==null) {
//            return;
//        }
//
//        if(inputChar == '\n') {
//            inputted = true;
//            return;
//        } else if(inputChar =='\b') {
//            if(typedTextLength >0) {
//                typedText.deleteCharAt(typedTextLength-1);
//            }
//        } else {
//            typedText.append(inputChar);
//            System.out.println(typedText.toString());
//        }
//
//    }
//
//    public StringBuilder getTypedText() {
//        return typedText;
//    }
//
//    public boolean isInputted() {
//        return inputted;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
