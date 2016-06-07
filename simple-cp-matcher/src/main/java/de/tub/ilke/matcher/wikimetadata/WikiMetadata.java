package de.tub.ilke.matcher.wikimetadata;

import java.util.Map;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType()
public class WikiMetadata {
	public String wikiName;
	// TODO start this list with the member variables of this class as keys.
	// although this would look better is it really needed?
	public Map<String, String> properties;
	@CsvField(pos = 1)
	public String general_features;

	public String getGeneral_features() {
		return general_features;
	}

	public void setGeneral_features(String general_features) {
		this.general_features = general_features;
	}

	public String version;
	public String last_release_date;
	public String author;
	public String url;
	public String free_and_open_source;
	public String license;
	public String programming_language;
	public String data_storage;
	public String license_cost_fee;
	public String development_status;
	public String intended_audience;

	public String hosting_features;
	public String storage_quota;
	public String bandwidth_quota;
	public String other_limits;
	public String topic_restrictions;
	public String corporate_branding;
	public String own_domain;
	public String ads_allowed;

	public String system_requirements;
	public String operating_system;
	public String root_access;
	public String webserver;

	public String other_requirements;

	public String datastorage;
	public String text_files;
	public String mysql;
	public String postgresql;
	public String oracle;
	public String sqlite;
	public String berkeleydb;
	public String rcs;
	public String other;

	public String security_antispam;
	public String page_permissions;
	public String acl;
	public String authentication_backends;
	public String host_blocking;
	public String mail_encryption;
	public String nofollow;
	public String blacklist;
	public String captcha;
	public String delayed_indexing;

	public String development_support;
	public String commercial_support;
	public String preconfigured_hosting;
	public String code_repository;
	public String issue_tracker;
	public String mailing_list;
	public String support_forum;
	public String irc_channel;

	public String common_features;
	public String preview;
	public String minor_changes;
	public String change_summary;
	public String page_history;
	public String page_revisions;
	public String revision_diffs;
	public String page_index;
	public String plugin_system;

	public String special_features;
	public String unicode_support;
	public String right2left_support;
	public String interface_languages;
	public String email_notification;
	public String comments;
	public String categories;
	public String namespaces;
	public String page_redirection;
	public String conflict_handling;
	public String search;
	public String wiki_farming;
	public String structured_data;

	public String links;
	public String camelcase;
	public String freelinks;
	public String backlinks;
	public String interwiki;
	public String sisterwiki;
	public String image_links;
	public String windows_shares;
	public String page_redirects;

	public String syntax_features;
	public String html_tags;
	public String math_formulas;
	public String tables;
	public String creole_support;
	public String markdown_support;
	public String textile_support;
	public String bbcode_support;
	public String emoticon_images;
	public String syntax_highlighting;
	public String footnotes;
	public String quoting;
	public String internal_comments;
	public String custom_styles;
	public String faq_tags;
	public String scripting;
	public String content_includes;
	public String feed_aggregation;

	public String usability;
	public String section_editing;
	public String page_templates;
	public String double_click_edit;
	public String toolbar;
	public String wysiwyg_editing;
	public String access_keys;
	public String auto_signature;

	public String statistics;
	public String recent_changes;
	public String wanted_pages;
	public String orphaned_pages;
	public String most_least_popular;
	public String recent_visitors;
	public String analysis;

	public String output;
	public String html;
	public String css_stylesheets;
	public String printer_friendly;
	public String mobile_friendly;
	public String themes_skins;
	public String rss_feeds;
	public String atom_feeds;
	public String abbreviations;
	public String auto_toc;
	public String raw_export;
	public String html_export;
	public String xml_export;
	public String pdf_export;

	public String media_and_files;
	public String file_attachments;
	public String media_revisions;
	public String embedded_flash;
	public String embedded_video;
	public String image_editing;
	public String svg_editing;
	public String mindmap_editing;
	public String media_search;

	public String extras;
	public String calendar;
	public String image_galleries;
	public String forums;
	public String blogs;
	public String ticket_system;
}
