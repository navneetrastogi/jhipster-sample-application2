import { Moment } from 'moment';

export const enum IllumineMediaType {
  PHOTO = 'PHOTO',
  VIDEO = 'VIDEO'
}

export interface IConversation {
  id?: string;
  text?: string;
  createdOn?: Moment;
  sentOn?: Moment;
  status?: string;
  fileUrl?: string;
  mediaType?: IllumineMediaType;
}

export const defaultValue: Readonly<IConversation> = {};
