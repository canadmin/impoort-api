package com.impoort.impoortapi.domain.watch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impoort.impoortapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Watching {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String watcherUser;

    private boolean isBeingWatch;

    @OneToOne
    @JoinColumn(name = "watcherUserID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    private UUID watchMapId;
}
