package jqa.maxim.starikov.soap;


import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTest {

  @Test
  public void testIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("77.88.21.11");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
