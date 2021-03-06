package cz.geokuk.plugins.kesoid.kind.waymark;

import cz.geokuk.plugins.kesoid.EKesVztah;
import cz.geokuk.plugins.kesoid.Wpt;
import cz.geokuk.plugins.kesoid.importek.GpxWpt;
import cz.geokuk.plugins.kesoid.kind.*;
import cz.geokuk.util.procak.EProcakResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WaymarkGpxWptProcak implements GpxWptProcak {
	private static final String WAYMARK = "Waymark";
	private static final String WM = "WM";

	private static final String GEOCACHE = "Geocache";
	private static final String GEOCACHE_FOUND = "Geocache Found";

	private final GpxToWptContext ctx;
	private final GpxToWptBuilder builder;


	@Override
	public EProcakResult process(final GpxWpt gpxwpt) {

		Waymark wm;
		if (isWaymarkNormal(gpxwpt)) {
			wm = createWaymarkNormal(gpxwpt);
		} else if (isWaymarkGeoget(gpxwpt)) {
			wm = createWaymarkGeoget(gpxwpt);
		} else {
			return EProcakResult.NEVER;
		}
		ctx.expose(wm.getMainWpt());
		return EProcakResult.DONE;
	}


	@Override
	public void roundDone() {
	}

	private boolean isWaymarkNormal(final GpxWpt gpxWpt) {
		return WAYMARK.equals(gpxWpt.sym) && gpxWpt.name.startsWith(WM);
	}

	private boolean isWaymarkGeoget(final GpxWpt gpxWpt) {
		return gpxWpt.name.startsWith(WM) && (GEOCACHE.equals(gpxWpt.sym) || GEOCACHE_FOUND.equals(gpxWpt.sym));
	}


	private Waymark createWaymarkGeoget(final GpxWpt gpxwpt) {
		final Waymark wm = new Waymark();
		wm.setIdentifier(gpxwpt.name);
		if (ctx.getGccomNick().name.equals(gpxwpt.groundspeak.placedBy)) {
			wm.setVztahx(EKesVztah.OWN);
		} else {
			wm.setVztahx(EKesVztah.NORMAL);
		}
		wm.setUrl(gpxwpt.link.href);
		wm.setAuthor(gpxwpt.groundspeak.placedBy);
		wm.setHidden(gpxwpt.time);

		final Wpt wpt = createWpt(gpxwpt);
		wpt.setNazev(gpxwpt.groundspeak.name);
		wpt.setSym(odstranNadbytecneMezery(gpxwpt.groundspeak.type));

		wm.addWpt(wpt);
		wm.setUserDefinedAlelas(ctx.definujUzivatslskeAlely(gpxwpt));
		return wm;
	}

	private Waymark createWaymarkNormal(final GpxWpt gpxwpt) {
		final Waymark wm = new Waymark();
		wm.setIdentifier(gpxwpt.name);
		if (gpxwpt.groundspeak != null) {
			if (ctx.getGccomNick().name.equals(gpxwpt.groundspeak.placedBy)) {
				wm.setVztahx(EKesVztah.OWN);
			} else {
				wm.setVztahx(EKesVztah.NORMAL);
			}
			wm.setAuthor(gpxwpt.groundspeak.placedBy);
		} else {
			wm.setVztahx(EKesVztah.NORMAL);
		}
		wm.setUrl(gpxwpt.link.href);

		final Wpt wpt = createWpt(gpxwpt);
		wpt.setNazev(gpxwpt.link.text);
		wpt.setSym(odstranNadbytecneMezery(gpxwpt.type));

		wm.addWpt(wpt);
		wm.setUserDefinedAlelas(ctx.definujUzivatslskeAlely(gpxwpt));

		return wm;
	}


	private Wpt createWpt(final GpxWpt gpxwpt) {
		return builder.createWpt(gpxwpt, WaymarkPlugin.WAYMARK);
	}

	/**
	 * @param aType
	 * @return
	 */
	private String odstranNadbytecneMezery(final String s) {
		return s == null ? null : s.replaceAll(" +", " ");
	}


	@Override
	public void allDone() {
		// TODO Auto-generated method stub

	}

}
