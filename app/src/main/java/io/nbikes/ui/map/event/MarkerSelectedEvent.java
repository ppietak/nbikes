package io.nbikes.ui.map.event;

public class MarkerSelectedEvent {
    private Long id;

    public MarkerSelectedEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
