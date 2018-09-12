import { Moment } from 'moment';

export const enum IllumineMediaType {
  PHOTO = 'PHOTO',
  VIDEO = 'VIDEO'
}

export interface IMedia {
  id?: string;
  mediaType?: IllumineMediaType;
  createdOn?: Moment;
  fileUrl?: string;
  title?: string;
}

export const defaultValue: Readonly<IMedia> = {};
