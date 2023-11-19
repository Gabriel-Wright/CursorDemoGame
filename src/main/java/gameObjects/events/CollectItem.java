package gameObjects.events;

import gameObjects.events.generic.Event;

public class CollectItem implements Event {


    @Override
    public void runEvent() {
        //add to inventorywd
        //ui popup
        //delete item from memory if possible? -- maybe need to think about thi
        System.out.println("Got item :))");
    }
}
