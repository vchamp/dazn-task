The application reads and shows events and schedule and allows playing an event video.

It has two tabs Events and Schedule. Each tab loads data individually. The state is preserved 
between tab switches. Schedule is reloaded every 30 seconds but only if the Schedule tab is active.

If the data cannot be loaded due to the missing connectivity then the error message is shown and 
connectivity state is observed to start reloading when it is available.

The project is not divided into modules, but the packages structure is made with a possible 
modularization in mind and will help make it easier.
