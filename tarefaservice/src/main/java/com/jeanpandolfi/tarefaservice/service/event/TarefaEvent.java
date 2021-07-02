package com.jeanpandolfi.tarefaservice.service.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaEvent extends BaseEvent{
    public TarefaEvent(Long id) {
        super(id);
    }
}
