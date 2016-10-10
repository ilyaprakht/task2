package view;

import view.event.output.OutputEvent;


public interface ViewObserver {

    void handleEvent(OutputEvent event);

}
