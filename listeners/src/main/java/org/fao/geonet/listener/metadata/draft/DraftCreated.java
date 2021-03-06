/*
 * Copyright (C) 2001-2016 Food and Agriculture Organization of the
 * United Nations (FAO-UN), United Nations World Food Programme (WFP)
 * and United Nations Environment Programme (UNEP)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 *
 * Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
 * Rome - Italy. email: geonetwork@osgeo.org
 */

package org.fao.geonet.listener.metadata.draft;

import java.util.Arrays;

import jeeves.server.context.ServiceContext;
import jeeves.server.dispatchers.ServiceManager;
import org.fao.geonet.constants.Geonet;
import org.fao.geonet.domain.AbstractMetadata;
import org.fao.geonet.domain.MetadataDraft;
import org.fao.geonet.events.md.MetadataDraftAdd;
import org.fao.geonet.kernel.datamanager.IMetadataIndexer;
import org.fao.geonet.kernel.datamanager.IMetadataUtils;
import org.fao.geonet.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * If an approved metadata gets removed, remove all draft associated to it.
 * <p>
 * This doesn't need to be disabled if no draft is used, as it only removes
 * drafts.
 *
 * @author delawen
 */
@Component
public class DraftCreated implements ApplicationListener<MetadataDraftAdd> {

    @Autowired
    private IMetadataUtils metadataUtils;

    @Autowired
    private IMetadataIndexer metadataIndexer;

    @Autowired
    ServiceManager serviceManager;

    @Override
    public void onApplicationEvent(MetadataDraftAdd event) {
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void doAfterCommit(MetadataDraftAdd event) {
        Log.trace(Geonet.DATA_MANAGER, "Reindexing non drafted versions of uuid " + event.getMd().getUuid());
        try (ServiceContext context = serviceManager.createServiceContext("draft_created", -1)) {
            for (AbstractMetadata md : metadataUtils.findAllByUuid(event.getMd().getUuid())) {
                if (!(md instanceof MetadataDraft)) {
                    Log.trace(Geonet.DATA_MANAGER, "Reindexing " + md.getId());
                    try {
                        metadataIndexer.indexMetadata(Arrays.asList(String.valueOf(md.getId())));
                    } catch (Exception e) {
                        Log.error(Geonet.DATA_MANAGER, e, e);
                    }
                }
            }
        } catch (Throwable e) {
            Log.error(Geonet.DATA_MANAGER, "Couldn't reindex the non drafted versions of " + event.getMd(), e);
        }
    }

}
