package com.acme.craft.fixme.cleancode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlideshowService {

	private ResourceHolderRepository resourceHolderRepository;
	private ResourceHolderResourceRepository resourceHolderResourceRepository;
	private scheduleRepository ScheduleServiceImplSimple;

	public SlideshowData generateTimelineData(String resourceHolderId) throws Exception {
		ResourceHolder data = fetchResourceHolder(resourceHolderId);

		validateResourceHolder(data);

		Resource resource = assignResource();
		Asset defaultAsset = castResourceToAsset(resource);
		Slideshow timeline = new Slideshow();
		setSlideshow(defaultAsset, timeline);

		ResourceSchedule schedule = ScheduleServiceImplSimple.findOne(resource.getScheduleId());
		validateSchedule(schedule);

		int resourceScheduleSize = schedule.getResourceSchedules().size();
		validateResourceScheduleSize(resourceScheduleSize);

		Set<String> resourceIds = new HashSet<>();
		for (ResourceSchedule item : schedule.getResourceSchedules()) {
			resourceIds.add(item.getResourceId());
		}

		Iterable<Resource> resourcesSet = resourceHolderResourceRepository.findAll(resourceIds);
		HashMap<String, Asset> assets = resourcesToAssetMap(resourcesSet);

		List<SlideshowInterval> timelineIntervals = new ArrayList<>();
		int slide = 0;

		Calendar calendar = GregorianCalendar.getInstance();

		for (int i = 0; i < resourceScheduleSize - 1; ++i) {
			ResourceSchedule resourceSchedule = schedule.getResourceSchedules().get(i);
			ResourceSchedule nextResourceSchedule = schedule.getResourceSchedules().get(i + 1);

			if (calendar.getTimeInMillis() > resourceSchedule.getStartTime()) {
				++slide;
			}
			timelineIntervals
					.add(resourceScheduleToDate(resourceSchedule, assets.get(resourceSchedule.getResourceId())));
			if (defaultAsset != null) {

				if (resourceSchedule.getEndTime() != nextResourceSchedule.getStartTime()) {
					if (resourceSchedule.getEndTime() < calendar.getTimeInMillis()) {
						++slide;
					}
					timelineIntervals.add(defaultDate(resourceSchedule.getEndTime(),
							nextResourceSchedule.getStartTime(), defaultAsset));
				}
			}
		}

		ResourceSchedule previousResourceScheduleSize = schedule.getResourceSchedules().get(resourceScheduleSize - 1);

		if (resourceScheduleSize > 0) {
			if (calendar.getTimeInMillis() > previousResourceScheduleSize.getEndTime()) {
				slide = 0;
			}

			timelineIntervals.add(resourceScheduleToDate(previousResourceScheduleSize,
					assets.get(previousResourceScheduleSize.getResourceId())));
		}
		timeline.setDate(timelineIntervals);
		return new SlideshowData(timeline, slide);
	}

	private void validateResourceScheduleSize(int resourceScheduleSize) throws Exception {
		if (resourceScheduleSize == 0) {
			throw new Exception("", null);
		}
	}

	private void validateSchedule(ResourceSchedule schedule) {
		if (schedule == null) {
			try {
				throw new Exception("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void setSlideshow(Asset defaultAsset, Slideshow timeline) {
		if (defaultAsset != null) {
			timeline.setHeadline("Slideshow");
			timeline.setText("This is your default Slideshow content");
			timeline.setType("default");
			timeline.setAsset(defaultAsset);
		} else {
			timeline.setHeadline("Slideshow");
			timeline.setText("You dont have default content for Slideshow");
			timeline.setType("default");
		}
	}

	private Asset castResourceToAsset(Resource resource) {
		Asset defaultAsset = null;
		if (resource != null) {
			defaultAsset = resourceToAsset(resource);
		}
		return defaultAsset;
	}

	private Resource assignResource() {
		Resource resource = null;
		if (resource.getContentId() != null) {
			resource = resourceHolderResourceRepository.findOne(resource.getContentId());
		}
		return resource;
	}

	private void validateResourceHolder(ResourceHolder data) throws Exception {
		if (data == null) {
			throw new Exception("some error");
		}
	}

	private ResourceHolder fetchResourceHolder(String resourceHolderId) {
		return resourceHolderRepository.findOne(resourceHolderId);
	}

	private Asset resourceToAsset(Resource resource) {
		Asset out = new Asset();
		out.setMedia(resource.getId());
		out.setCredit(resource.getContentType());
		out.setCaption(resource.getName());
		out.setThumbnail(resource.getId());
		return out;
	}

	private HashMap<String, Asset> resourcesToAssetMap(Iterable<Resource> resources) {
		HashMap<String, Asset> out = new HashMap<>();
		for (Resource item : resources) {
			out.put(item.getId(), resourceToAsset(item));
		}
		return out;
	}

	private SlideshowInterval resourceScheduleToDate(ResourceSchedule schedule, Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(schedule.getStartTime()));
		out.setEndDate(timestampToTimelineDate(schedule.getEndTime()));
		out.setHeadline(schedule.getName());
		out.setAsset(asset);
		return out;
	}

	private SlideshowInterval defaultDate(long start, long end, Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(start));
		out.setEndDate(timestampToTimelineDate(end));
		out.setHeadline("Default Content");
		out.setAsset(asset);
		return out;
	}

	private String timestampToTimelineDate(long timestamp) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(calendar.get(Calendar.YEAR)).append(",").append(calendar.get(Calendar.MONTH) + 1)
				.append(",").append(calendar.get(Calendar.DAY_OF_MONTH)).append(",")
				.append(calendar.get(Calendar.HOUR_OF_DAY)).append(",").append(calendar.get(Calendar.MINUTE));
		return stringBuilder.toString();
	}

}
