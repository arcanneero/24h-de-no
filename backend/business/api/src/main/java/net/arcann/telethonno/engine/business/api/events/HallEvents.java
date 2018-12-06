package net.arcann.telethonno.engine.business.api.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import net.arcann.telethonno.engine.business.api.view.JoueurView;

import java.util.List;

@Data
@AllArgsConstructor
public class HallEvents implements SseWriteableEvent {

    private List<JoueurView> joueurs;

}