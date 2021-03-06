package cz.geokuk.plugins.kesoid.kind;

import java.util.Map;
import java.util.TreeMap;

import cz.geokuk.plugins.kesoidpopisky.PopiskyNahrazovac;
import cz.geokuk.plugins.kesoidpopisky.SestavovacPopisku;

public abstract class PopiskyDefBuilder0 {

	protected String label;
	protected String defaultPattern;
	private final Map<String, PopiskyNahrazovac> nahrazovace = new TreeMap<>();

	protected void def(final String key, final PopiskyNahrazovac nahrazovac) {
		nahrazovace.put(key, nahrazovac);
	}

	public final PopiskyDefBuilder0 doInit()  {
		def("{wpt}", (sb, wpt) -> sb.append(wpt.getName()));


		def("{autor}", (sb, wpt) -> sb.append(wpt.getKesoid().getAuthor()));

		def("{nazev}", (sb, wpt) -> sb.append(wpt.getNazev()));

		def("{zalozeno}", (sb, wpt) -> sb.append(wpt.getKesoid().getHidden()));
		def("{nbsp}", new SestavovacPopisku.NahrKonstantni(" "));

		def("{br}", SestavovacPopisku.NAHRBR);

		def("{puvodnipotvora}", (sb, wpt) -> sb.append(wpt.getKesoid().getNazev()));

		init();
		return this;
	}

	public abstract void init();

	public PopiskyDef build() {
		return new PopiskyDef() {

			@Override
			public Map<String, PopiskyNahrazovac> getNahrazovace() {
				return nahrazovace;
			}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public String getDefaultPatter() {
				return defaultPattern;
			}

			@Override
			public String geHelpNahrazovace() {
				return nahrazovace.keySet().toString();
			}
		};
	}
}
