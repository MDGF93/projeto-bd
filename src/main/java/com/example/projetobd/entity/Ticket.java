package com.example.projetobd.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Session session;

    private Integer seatNumber;

    private TicketType ticketType;

    private boolean isCreditCard;

    public Ticket(Session session, Integer seatNumber, TicketType ticketType, boolean isCreditCard) {
        this.session = session;
        this.seatNumber = seatNumber;
        this.ticketType = ticketType;
        this.isCreditCard = isCreditCard;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return id != null && Objects.equals(id, ticket.id);
    }



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
