//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.04.08 à 01:07:41 PM CEST 
//


package com.daloji.caching.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="engine" use="required" type="{http://com.daloji.caching/}engine" /&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ipAdress" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="port" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="ttlKey" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "cacheSettings", namespace = "http://com.daloji.caching/")
public class CacheSettings {

    @XmlAttribute(name = "engine", required = true)
    protected Engine engine;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "ipAdress", required = true)
    protected String ipAdress;
    @XmlAttribute(name = "port", required = true)
    protected int port;
    @XmlAttribute(name = "ttlKey", required = true)
    protected int ttlKey;

    /**
     * Obtient la valeur de la propriété engine.
     * 
     * @return
     *     possible object is
     *     {@link Engine }
     *     
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Définit la valeur de la propriété engine.
     * 
     * @param value
     *     allowed object is
     *     {@link Engine }
     *     
     */
    public void setEngine(Engine value) {
        this.engine = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété ipAdress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAdress() {
        return ipAdress;
    }

    /**
     * Définit la valeur de la propriété ipAdress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAdress(String value) {
        this.ipAdress = value;
    }

    /**
     * Obtient la valeur de la propriété port.
     * 
     */
    public int getPort() {
        return port;
    }

    /**
     * Définit la valeur de la propriété port.
     * 
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * Obtient la valeur de la propriété ttlKey.
     * 
     */
    public int getTtlKey() {
        return ttlKey;
    }

    /**
     * Définit la valeur de la propriété ttlKey.
     * 
     */
    public void setTtlKey(int value) {
        this.ttlKey = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
