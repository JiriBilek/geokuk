/**
 * 
 */
package cz.geokuk.plugins.kesoidpopisky;

import java.util.EnumMap;

import cz.geokuk.framework.Copyable;
import cz.geokuk.framework.Preferenceble;
import cz.geokuk.plugins.kesoid.data.EKesoidKind;

/**
 * @author veverka
 *
 */
@Preferenceble
public class PopiskyPatterns implements Copyable<PopiskyPatterns> {

  private String kesPattern = "{info} - {nazev} ({wpt})";
  private String waymarkPattern = "{nazev} ({wpt})";
  private String cgpPattern = "{wpt}";
  private String simplewaypointPattern = "{nazev} ({wpt})";
  private String geospyPattern = "{nazev} ({wpt})";
  private String munzeePattern = "{nazev} ({wpt})";


  public EnumMap<EKesoidKind, String> asMap() {
    EnumMap<EKesoidKind, String> map = new EnumMap<EKesoidKind, String>(EKesoidKind.class);
    map.put(EKesoidKind.KES, kesPattern);
    map.put(EKesoidKind.GEOSPY, geospyPattern);
    map.put(EKesoidKind.MUNZEE, munzeePattern);
    map.put(EKesoidKind.WAYMARK, waymarkPattern);
    map.put(EKesoidKind.CGP, cgpPattern);
    map.put(EKesoidKind.SIMPLEWAYPOINT, simplewaypointPattern);
    return map;
  }



  /**
   * @return the kesPattern
   */
  public String getKesPattern() {
    return kesPattern;
  }



  /**
   * @param kesPattern the kesPattern to set
   */
  public void setKesPattern(String kesPattern) {
    this.kesPattern = kesPattern;
  }

  
  /**
   * @return the geospyPattern
   */
  public String getGeospyPattern() {
    return geospyPattern;
  }

  /**
   * @param geospyPattern the geospyPattern to set
   */
  public void setGeospyPattern(String geospyPattern) {
    this.geospyPattern = geospyPattern;
  }
  
  
  
    /**
   * @return the munzeePattern
   */
  public String getMunzeePattern() {
    return munzeePattern;
  }

  /**
   * @param munzeePattern the munzeePattern to set
   */
  public void setMunzeePattern(String munzeePattern) {
    this.munzeePattern = munzeePattern;
  }
  
  

  /**
   * @return the waymarkPattern
   */
  public String getWaymarkPattern() {
    return waymarkPattern;
  }



  /**
   * @param waymarkPattern the waymarkPattern to set
   */
  public void setWaymarkPattern(String waymarkPattern) {
    this.waymarkPattern = waymarkPattern;
  }



  /**
   * @return the cgpPattern
   */
  public String getCgpPattern() {
    return cgpPattern;
  }



  /**
   * @param cgpPattern the cgpPattern to set
   */
  public void setCgpPattern(String cgpPattern) {
    this.cgpPattern = cgpPattern;
  }



  /**
   * @return the simplewaypointPattern
   */
  public String getSimplewaypointPattern() {
    return simplewaypointPattern;
  }



  /**
   * @param simplewaypointPattern the simplewaypointPattern to set
   */
  public void setSimplewaypointPattern(String simplewaypointPattern) {
    this.simplewaypointPattern = simplewaypointPattern;
  }



  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PopiskyPatterns [kesPattern=" + kesPattern + ", geospyPattern=" + geospyPattern + ", munzeePattern=" + munzeePattern + ", waymarkPattern=" + waymarkPattern + ", cgpPattern=" + cgpPattern + ", simplewaypointPattern="
    + simplewaypointPattern + "]";
  }



  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cgpPattern == null) ? 0 : cgpPattern.hashCode());
    result = prime * result + ((kesPattern == null) ? 0 : kesPattern.hashCode());
    result = prime * result + ((simplewaypointPattern == null) ? 0 : simplewaypointPattern.hashCode());
    result = prime * result + ((waymarkPattern == null) ? 0 : waymarkPattern.hashCode());
    result = prime * result + ((geospyPattern == null) ? 0 : geospyPattern.hashCode());
    result = prime * result + ((munzeePattern == null) ? 0 : munzeePattern.hashCode());
    return result;
  }



  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PopiskyPatterns other = (PopiskyPatterns) obj;
    if (cgpPattern == null) {
      if (other.cgpPattern != null)
        return false;
    } else if (!cgpPattern.equals(other.cgpPattern))
      return false;
    if (kesPattern == null) {
      if (other.kesPattern != null)
        return false;
    } else if (!kesPattern.equals(other.kesPattern))
      return false;
    if (simplewaypointPattern == null) {
      if (other.simplewaypointPattern != null)
        return false;
    } else if (!simplewaypointPattern.equals(other.simplewaypointPattern))
      return false;
    if (waymarkPattern == null) {
      if (other.waymarkPattern != null)
        return false;
    } else if (!waymarkPattern.equals(other.waymarkPattern))
      return false;
    if (geospyPattern == null) {
      if (other.geospyPattern != null)
        return false;
    } else if (!geospyPattern.equals(other.geospyPattern))
      return false;
    if (munzeePattern == null) {
      if (other.munzeePattern != null)
        return false;
    } else if (!munzeePattern.equals(other.munzeePattern))
      return false;
    return true;
  }


  @Override
  public PopiskyPatterns copy() {
    try {
      return (PopiskyPatterns) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

}
