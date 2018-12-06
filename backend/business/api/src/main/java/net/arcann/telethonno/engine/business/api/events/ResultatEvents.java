package net.arcann.telethonno.engine.business.api.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import net.arcann.telethonno.engine.business.api.view.JoueurView;
import net.arcann.telethonno.engine.business.api.view.ResultatView;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultatEvents implements SseWriteableEvent {

    private ResultatView resultats;

}