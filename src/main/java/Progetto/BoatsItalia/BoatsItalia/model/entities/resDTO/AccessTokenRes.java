package Progetto.BoatsItalia.BoatsItalia.model.entities.resDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Questa classe rappresenta un oggetto di risposta contenente un access token.
 */
@Data // Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString

public class AccessTokenRes {
    private String accessToken; // Campo per contenere l'access token

    // Costruttore generato da Lombok con un parametro per l'access token
    public AccessTokenRes(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter generato da Lombok per l'access token

    public String getAccessToken() {
        return accessToken;
    } // Fine del metodo getAccessToken

    // Setter generato da Lombok per l'access token

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    } // Fine del metodo setAccessToken

    // Metodo equals generato da Lombok per confrontare due oggetti AccessTokenRes

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccessTokenRes other)) return false;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accessToken = this.getAccessToken();
        final Object other$accessToken = other.getAccessToken();
        return Objects.equals(this$accessToken, other$accessToken);
    } // Fine del metodo equals

    private boolean canEqual(Object o) {
        return o instanceof AccessTokenRes;
    }

    // Metodo hashCode generato da Lombok per calcolare l'hash code dell'oggetto AccessTokenRes

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        return result;
    } // Fine del metodo hashCode

    // Metodo toString generato da Lombok per ottenere una rappresentazione testuale dell'oggetto AccessTokenRes

    public String toString() {
        return "AccessTokenRes(accessToken=" + this.getAccessToken() + ")";
    } // Fine del metodo toString
}