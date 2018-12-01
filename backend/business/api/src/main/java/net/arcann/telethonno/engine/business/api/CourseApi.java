package net.arcann.telethonno.engine.business.api;

import net.arcann.telethonno.engine.business.api.view.PisteView;

import java.util.List;

public interface CourseApi {

    PisteView nouveauTour(PisteView piste);

    List<PisteView> get();

}