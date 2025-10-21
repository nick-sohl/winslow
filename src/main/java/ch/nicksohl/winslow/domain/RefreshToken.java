package ch.nicksohl.winslow.domain;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
 
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String tokenHash;  // store hash(token)

    @Column(nullable = false)
    private Instant issuedAt;

    @Column(nullable = false)
    private Instant expiresAt;

    private Instant lastUsedAt;

    @Column(nullable = false)
    private boolean revoked = false;

    private String replacedByToken;  // optional rotation tracking

    private String userAgent;
    private String ipAddress;
}
