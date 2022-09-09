package org.madbit.r2dbc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEST_TIMEOUT_2")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class TestTimeout2 {
    @Id
    private Long id;
}
