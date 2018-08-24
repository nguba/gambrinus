package me.nguba.gambrinus.event;

import java.time.Instant;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class MutatorEvent {

	protected final long timestamp;

	public MutatorEvent(Instant instant) {
		timestamp = instant.toEpochMilli();
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MutatorEvent other = (MutatorEvent) obj;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
}
