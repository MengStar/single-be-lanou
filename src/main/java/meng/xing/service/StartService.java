package meng.xing.service;

import meng.xing.entity.Answer;

public interface StartService {
    void start();

    void end();

    boolean complete(Answer answer);

}
