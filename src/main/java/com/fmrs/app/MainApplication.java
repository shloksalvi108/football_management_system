package com.fmrs.app;

import com.fmrs.ui.awt.MainFrame;

public class MainApplication {

    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        SearchUseCase searchUseCase = config.searchUseCase();
        MainFrame.launch(searchUseCase);
    }
}

