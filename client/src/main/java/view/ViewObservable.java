package view;

import view.event.output.OutputEvent;


public interface ViewObservable {

    void addObserver(ViewObserver observer);

    void removeObserver(ViewObserver observer);

    void notifyObservers(OutputEvent event);

}
