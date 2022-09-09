package org.madbit.r2dbc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEST_TIMEOUT")
//@org.springframework.data.relational.core.mapping.Table(value = "TEST_TIMEOUT")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class TestTimeout {
    @Id
    private Long id;
}
