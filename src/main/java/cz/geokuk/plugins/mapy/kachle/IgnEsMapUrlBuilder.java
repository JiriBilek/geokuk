package cz.geokuk.plugins.mapy.kachle;

import java.net.MalformedURLException;
import java.net.URL;

public class IgnEsMapUrlBuilder implements KachleUrlBuilder {

	private final String urlBase;

	public IgnEsMapUrlBuilder(final String urlBase) {
		this.urlBase = urlBase;
	}

	@Override
	public URL buildUrl(final KaOne kaOne) throws MalformedURLException {

		final StringBuilder sb = new StringBuilder();
		final KaLoc kaloc = kaOne.getLoc();
		
//http://www.ign.es/wmts/pnoa-ma?request=getTile&layer=OI.OrthoimageCoverage&TileMatrixSet=GoogleMapsCompatible&TileMatrix=17&TileCol=66761&TileRow=49768&format=image/jpeg
		sb.append(urlBase);
		sb.append("?request=getTile&layer=OI.OrthoimageCoverage&TileMatrixSet=GoogleMapsCompatible&TileMatrix=");
		sb.append(kaloc.getMoumer());
		sb.append("&TileCol=");
		sb.append(kaloc.getFromSzUnsignedX());
		sb.append("&TileRow=");
		sb.append(kaloc.getFromSzUnsignedY());
		sb.append("&format=image/png");
		
		final URL url = new URL(sb.toString());
		return url;
	}

}
