import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIncidentRecord, defaultValue } from 'app/shared/model/incident-record.model';

export const ACTION_TYPES = {
  FETCH_INCIDENTRECORD_LIST: 'incidentRecord/FETCH_INCIDENTRECORD_LIST',
  FETCH_INCIDENTRECORD: 'incidentRecord/FETCH_INCIDENTRECORD',
  CREATE_INCIDENTRECORD: 'incidentRecord/CREATE_INCIDENTRECORD',
  UPDATE_INCIDENTRECORD: 'incidentRecord/UPDATE_INCIDENTRECORD',
  DELETE_INCIDENTRECORD: 'incidentRecord/DELETE_INCIDENTRECORD',
  RESET: 'incidentRecord/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIncidentRecord>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type IncidentRecordState = Readonly<typeof initialState>;

// Reducer

export default (state: IncidentRecordState = initialState, action): IncidentRecordState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INCIDENTRECORD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INCIDENTRECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INCIDENTRECORD):
    case REQUEST(ACTION_TYPES.UPDATE_INCIDENTRECORD):
    case REQUEST(ACTION_TYPES.DELETE_INCIDENTRECORD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INCIDENTRECORD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INCIDENTRECORD):
    case FAILURE(ACTION_TYPES.CREATE_INCIDENTRECORD):
    case FAILURE(ACTION_TYPES.UPDATE_INCIDENTRECORD):
    case FAILURE(ACTION_TYPES.DELETE_INCIDENTRECORD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INCIDENTRECORD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INCIDENTRECORD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INCIDENTRECORD):
    case SUCCESS(ACTION_TYPES.UPDATE_INCIDENTRECORD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INCIDENTRECORD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/incident-records';

// Actions

export const getEntities: ICrudGetAllAction<IIncidentRecord> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INCIDENTRECORD_LIST,
  payload: axios.get<IIncidentRecord>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IIncidentRecord> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INCIDENTRECORD,
    payload: axios.get<IIncidentRecord>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIncidentRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INCIDENTRECORD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIncidentRecord> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INCIDENTRECORD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIncidentRecord> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INCIDENTRECORD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
