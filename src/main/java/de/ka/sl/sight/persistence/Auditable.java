package de.ka.sl.sight.persistence;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/** @author Sebastian Luther (https://github.com/luthesebas) */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @LastModifiedBy
    protected T modifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date modifiedDate;

    @Column(nullable = false, updatable = false)
    @CreatedBy
    protected T createdBy;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

}
