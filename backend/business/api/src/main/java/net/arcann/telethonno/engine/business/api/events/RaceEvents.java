package net.arcann.telethonno.engine.business.api.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.PisteView;

import java.util.List;

@Data
@AllArgsConstructor
public class RaceEvents implements SseWriteableEvent {

    private List<PisteView> pistes;

}