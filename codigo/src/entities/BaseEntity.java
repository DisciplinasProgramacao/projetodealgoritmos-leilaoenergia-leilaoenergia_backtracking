package entities;

import lombok.Data;

@Data
public abstract class BaseEntity {

    protected Long id;

    private Integer quantidade;

    private static Long proximoId = 1L;

    public BaseEntity(Integer quantidade) {
        this.quantidade = quantidade;
        this.id = proximoId++;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
