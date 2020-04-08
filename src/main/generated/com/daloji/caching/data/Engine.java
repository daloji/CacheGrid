//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.04.08 à 01:07:41 PM CEST 
//


package com.daloji.caching.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour engine.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="engine"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="REDIS"/&gt;
 *     &lt;enumeration value="MEMCACHED"/&gt;
 *     &lt;enumeration value="HAZELCAST"/&gt;
 *     &lt;enumeration value="APACHEIGNITE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "engine", namespace = "http://com.daloji.caching/")
@XmlEnum
public enum Engine {

    REDIS,
    MEMCACHED,
    HAZELCAST,
    APACHEIGNITE;

    public String value() {
        return name();
    }

    public static Engine fromValue(String v) {
        return valueOf(v);
    }

}
