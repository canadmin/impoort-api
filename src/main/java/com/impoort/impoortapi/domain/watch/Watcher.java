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
public class Watcher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean isBeingWatch;

    private String watchingUser;
    @OneToOne
    @JoinColumn(name = "watchingUserID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    // watcher silinirken bulunması kolay olsun diye eklendi
    private UUID watchMapId;
}
