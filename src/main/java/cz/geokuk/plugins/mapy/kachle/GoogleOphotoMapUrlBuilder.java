package cz.geokuk.plugins.mapy.kachle;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleOphotoMapUrlBuilder implements KachleUrlBuilder {

	private final String urlBase;

	public GoogleOphotoMapUrlBuilder(final String urlBase) {
		this.urlBase = urlBase;
	}

	@Override
	public URL buildUrl(final KaOne kaOne) throws MalformedURLException {

		final StringBuilder sb = new StringBuilder();
		final KaLoc kaloc = kaOne.getLoc();

		//http://khm1.googleapis.com/kh?v=702&hl=cs&&x=7813&y=6367&z=14
		
		sb.append(urlBase);
		sb.append("?v=702&hl=cs&&x=");
		sb.append(kaloc.getFromSzUnsignedX());
		sb.append("&y=");
		sb.append(kaloc.getFromSzUnsignedY());
		sb.append("&z=");
		sb.append(kaloc.getMoumer());
		
		final URL url = new URL(sb.toString());
		return url;
	}

}
